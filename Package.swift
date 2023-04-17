// swift-tools-version:5.3
import PackageDescription
import Foundation

let version = ProcessInfo.processInfo.environment["VERSION_NAME"]

let xcFrameworkUrl = "https://maven.pkg.github.com/dariuszszlag/KBMultiplatform/com/dariusz/kbcore-kmmbridge/\(String(describing: version))/kbcore-kmmbridge-\(String(describing: version)).zip"

let checksumUrl = "https://maven.pkg.github.com/dariuszszlag/KBMultiplatform/com/dariusz/kbcore-kmmbridge/\(String(describing: version))/kbcore-kmmbridge-\(String(describing: version)).zip.sha256"

let checksumString = "3d484a39d3f2a037cdd7e7ca7a909e034552e03e957a2f20cf923e7a7e0f4719"

let packageName = "kbcore"

let package = Package(
    name: packageName,
    platforms: [
        .iOS(.v14)
    ],
    products: [
        .library(
            name: packageName,
            targets: [packageName]
        ),
    ],
    targets: [
        .binaryTarget(
            name: packageName,
            url: xcFrameworkUrl,
            checksum: checksumString
        )
        ,
    ]
)